section .bss
    pivot resd 1
    right resd 1
    left resd 1
    low resd 1

section .data
    list dd -10, 3, 4, 10, 0, 20, 22, 2, 9, 8,-233, 55555, 2323223, -5000, -2147483648; supporting numbers between  -2,147,483,647 - 2,147,483,647 (signed)
section .text
    global _start

_start:
    mov edi, 0 ; edi = 0 is a requirement for the getLastListIndex function
    mov [low], edi
    call _getLastListIndex ; store the last index in edi

    mov [pivot], edi ; we choose our last index as our pivot element
    mov eax, 0;
    mov ebx, [pivot] ;

; params:
;   eax as list start
;   ebx as list end

_checkListBounds: ;
    mov edi, 0
    call _getLastListIndex
    cmp eax, edi ; does the list index point to the indicator of the list end
    js _checkLowerListBound
    ret

_checkLowerListBound:
      cmp ebx, eax
      jns _checkEqualListBound
      ret

_checkEqualListBound:
    cmp eax, ebx
    jne _recursionRound
    ret
_recursionRound:
    mov [low], eax
    mov ecx , [list + ebx * 4]
    mov [left], eax; ; left
    mov [pivot], ebx ; pivot
    sub ebx, 1
    mov [right], ebx; right

_getNextRightAndLeft:
    mov edx, [pivot]
    mov edi, [left]

_findLeft:
    mov eax, [list + edi * 4] ; get value of edi our loop counter
    cmp [right], edi; check if left is smaller than right
    js _LeftFound ; jump if signed (negative)
    cmp [list + edx * 4], eax  ; check if we are above the pivot
    js _LeftFound; jumps if signed (negative)
    add edi, 1;
    jmp _findLeft

_LeftFound:
    mov [left], edi
    mov edi, [right]

_findRight:
    mov ebx, [list + edi * 4] ; get value of edi our loop counter
    cmp edi, [left] ; continue until right is under left
    js _RightFound ; jump if signed (negative)
    cmp ebx, [list + edx * 4] ; check if we are above the pivot
    js _RightFound; jumps if signed (negative)
    sub edi, 1
    jmp _findRight

_RightFound:
   mov [right], edi;

.swapLeftRight:
   mov eax, [left]
   mov ebx, [right]
   cmp ebx, eax ; if left and right are already in right order dont swap them
   js _checkContinueSearching

   mov ecx, [list + eax * 4]
   xchg ecx, [list + ebx * 4]
   mov [list + eax * 4], ecx

   mov [left], eax
   mov [right], ebx

; search while left <= right
_checkContinueSearching:
    cmp eax, ebx
    js _getNextRightAndLeft

_swapLeftPivot:
       mov eax, [left]
       mov ebx, [pivot]
       mov ecx, [list + eax * 4]
       xchg ecx, [list + ebx * 4]
       mov [list + eax * 4], ecx
       mov [pivot], ebx
       mov [left], eax


.determineRecursion:
    mov edi, 0
    call _getLastListIndex
    push ax ; we need to store ax (left) inside the stack
    call _startCircleLeft
    pop ax
    call _startCircleRight
    jmp _interrupt


_startCircleLeft:
        mov ebx, eax
        mov eax, [low]
        sub ebx, 1
        call _checkListBounds
        ret

_startCircleRight:
    call _getLastListIndex ; store the last index in edi
    mov eax, [left]
    add eax, 1
    mov ebx, edi
    call _checkListBounds
    ret

; returns the length of the list in edi
;
; params:
;   - edi = 0
; return:
;   - edi

_getLastListIndex:
    mov edx, [list + edi * 4]
    add edi, 1
    cmp edx, -2147483648 ; the smallest number possible indicates the end of our list
    jnz _getLastListIndex
    sub edi, 2 ; edi is the index of the end_marker + 1 so we need to subtract it with 2 to get the index of the last number
    ret;

_interrupt:
    mov rax, 60
    syscall


