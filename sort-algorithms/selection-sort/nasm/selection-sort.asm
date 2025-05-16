section .bss
    num_buf resb 4  ; max 3 digits + space for calc


section .data
    newline db 10

     ; example input data
    input db 12, 2, 25, 8, 3, 17, 6, 20, 1, -1 ; -1 indicates the end of the list as we wont sort negative numbers
    startIndex db 0 ; the start index for loop
    currentIndex db 0  ; the current index of the loop
    smallestFound db 0xff  ; the current smallest found number
    smallestIndex db 0
    inputLength db 9


section .text
global _start

; 1. iterate through the array
; call an compare function on each iteration
; store the smallest number into an register like edx
; after the loop swap swap index with found biggest number and increase index

_start:
    mov al, 0xff
    mov [smallestFound], al ; reset eax on each loop count
    mov [smallestIndex], al
    mov cl, [startIndex]
    mov dl, [inputLength]
    cmp dl, cl
    je _interrupt
    mov edi, ecx
    call _findNextSmallestNumber ; returns to _start


_interrupt:
    int 0x80


; searching from start index to list end for next smallest number
; params: edi: startIndex for i iteration
;         ecx: including the start index

_findNextSmallestNumber:
    mov al, [input + edi]
    mov bl, [smallestFound]
    mov cl, [smallestIndex]
    cmp eax, ebx
    cmovl ebx, eax; conditional move statement if lower
    mov al, cl
    cmovl eax, edi
    mov [smallestFound], bl
    mov [smallestIndex], al
    mov edx, [inputLength]
    cmp edi, edx ; edi is i (loop count) and edx is the total length of the input
    je _swap ; jumps if equal
    mov eax, 1
    add edi, eax
    jmp _findNextSmallestNumber

; cl containg the [smallestIndex]
;  bl containg the [smallestFound]
_swap:
    mov al, [startIndex] ; store start index
    mov bl, [input + eax] ; store the value inside the start index
    mov dl, [input + ecx] ; store the value of the other value to swap

    mov [input + eax], dl ; swap both  values
    mov [input + ecx], bl
    jmp _loopAndCount

_loopAndCount:
    mov al, [startIndex]
    mov bl, 1
    add al, bl
    mov [startIndex], al
    jmp _start