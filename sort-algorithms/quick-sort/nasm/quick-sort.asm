section .bss
    pivot resd 1
    high resd 1
    low resd 1

section .data
    list dd -10, 20, 4, 10, 0, 20, 22, 2, 9, 8, -2147483648; supporting numbers between  -2,147,483,647 - 2,147,483,647 (signed)

section .text
    global _start

_start:
    mov edi, 0 ; edi = 0 is a requirement for the getLastListIndex function
    call _getLastListIndex ; store the last index in edi
    mov [pivot], edi ; we choose our last index as our pivot element
    mov eax, [low];
    mov ebx, [pivot] ;

; params:
;   eax as list start
;   ebx as list end

_recursionRound:
    mov ecx , [list + ebx * 4]
    mov [low], eax; ; left
    mov [pivot], ebx ; pivot
    sub ebx, 1
    mov [high], ebx; right

_getNextRightAndLeft:
    mov edx, [pivot]
    mov edi, [low]

_findLeft:
    mov eax, [list + edi * 4]
    cmp edi, [high] ; (edi < high)
    jns _LeftFound ; jump if not signed (positive or equals)
    cmp eax, [list + edx * 4]  ; move until
    jns _LeftFound; jumps if signed (negative)
    add edi, 1;
    jmp _findLeft

_LeftFound:
    mov [low], edi
    mov edi, [high]
_findRight:
    mov ebx, [list + edi * 4]
    cmp [low], edi
    jns _RightFound ; jump if not signed (positive or equals)
    cmp ebx, [list + edx * 4] ; move until
    js _RightFound; jumps if signed (negative)
    sub edi, 1
    jmp _findRight

_RightFound:
   mov [high], edi;

.swapLeftRight:
   mov eax, [low]
   mov ebx, [high]

   mov ecx, [list + eax * 4]
   xchg ecx, [list + ebx * 4]
   mov [list + eax * 4], ecx

; search while low <= high
.checkContinueSearching:
    cmp eax, ebx
    js _getNextRightAndLeft

.swapLeftPivot:
       mov ebx, [pivot]
       mov ecx, [list + eax * 4]
       xchg ecx, [list + ebx * 4]
       mov [list + eax * 4], ecx

.determineRecursion:
    call _getLastListIndex
    cmp ebx, edi
    je _startCircleRight
    mov ebx, [low]
    cmp ebx, 0
    je _startCircleLeft
    jmp _interrupt

_startCircleLeft:
        mov eax, 0
        mov ebx, [low]
        sub ebx, 1
        call _recursionRound

_startCircleRight:
    call _getLastListIndex ; store the last index in edi
    mov eax, [low]
    add eax, 1
    mov ebx, edi
    call _recursionRound


; returns the length of the list in edi
;
; params:
;   - edi = 0
; return:
;   - edi

_getLastListIndex:
    mov eax, [list + edi * 4]
    add edi, 1
    cmp eax, -2147483648 ; the smallest number possible indicates the end of our list
    jnz _getLastListIndex
    sub edi, 2 ; edi is the index of the end_marker + 1 so we need to subtract it with 2 to get the index of the last number
    ret;

_interrupt:
    mov rax, 60
    syscall


