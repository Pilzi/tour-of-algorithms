section .bss
    swapped resb 1 ; We the byte as boolean 0 is false and everything else is in our context true

section .data
    lastIndex dd -2147483648 ; The last index inside our list
    list dd -10, 3, 4, 10, 0, 20, 22, 2, 9, 8,-233, 55555, 2323223, -5000, -2147483648; Supporting numbers between  -2,147,483,647 - 2,147,483,647 (signed)

section .text
    global _start

; Start is there to clarify the start of the sort algorithm
_start:

    ; Setup everything like resetting is swapped and the loop counter
    _prepareLoop:
           mov edi, 1 ; Edi contains our list start index (i). + 1 because I always compare with i - 1
           mov eax, 0
           mov [swapped], eax ; We use the byte as boolean 0 is false and everything else is in our context true
           jmp _loopAndSwap

        ; Loop through each bubble and find swaps
        _loopAndSwap:
            mov eax, [list + edi * 4]
            mov ebx, [list + edi * 4 - 4]
            cmp eax, ebx ; If this results in a negative value (signed) the swap condition has been met (ebx < eax)
            js _swap
            add edi, 1
            mov eax, [list + edi * 4]
            mov ebx, [lastIndex]
            cmp eax, ebx ; Loop until we reached the last index of our list
            jne _loopAndSwap

        ; We run the bubble sort algorithm over and over again until we find nothing to sort in one iteration ([swapped] == 0)
        _isSwapped:
            mov eax, 0
            cmp [swapped], eax ;
            je _interrupt ; We are done because nothing has swapped in our last iteration
            jmp _prepareLoop

; Swap edi with edi - 1 and set swapped to true
_swap:
    mov ecx, [list + edi * 4]
    mov eax, [list + edi * 4 - 4]
    mov [list + edi * 4], eax
    mov [list + edi * 4 - 4], ecx ;
    mov eax, 1
    mov [swapped], eax ; swapped is true for this iteration
    jmp _loopAndSwap

_interrupt:
    mov rax, 60
    syscall


