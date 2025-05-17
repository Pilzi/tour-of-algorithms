section .data
    ;list db 12, 2, 25, 8, 3, 17, 6, 20, 1, -1 ; -1 indicates the end of the list as we wont sort negative numbers
    list db 70, 8, 2, 3, 5, 9, 6, -1 ; -1 indicates the end of the list as we wont sort negative numbers
    startIndex db 0 ; the start index for loop
    minIndex db 0 ; the index of the smallest num found

section .text
    global _start

; start of the loop
; resets the minIndex
; and check if we are done with sorting

_start:
    mov cl, [startIndex]
    mov dl, [list + ecx] ; check if the value at the start index is the end of the list (-1)
    cmp dl, -1 ; jump to interrupt if we are done
    je _interrupt
    mov edi, [startIndex] ; edi is the j counter for the second loop

; iterate through the list and store the smallest number found in minIndex
.findNextSmallestNumber:
     mov al, [list + edi] ; store number for current iteration
     mov cl, [minIndex]
     mov bl, [list + ecx] ; get the value at minIndex
     cmp eax, ebx
     cmovl ecx, edi; conditional move statement if eax < ebx
     mov [minIndex], ecx
     mov eax, 1 ;
     add edi, eax ; increase the loop counter edi
     mov al, [list + edi] ; get the number for the next iteration
     cmp al, -1 ; if the next iteration is -1 we are done
     jne .findNextSmallestNumber ; if we are not done, loop again

.swapValues:

     mov al, [startIndex] ; get the index of the value we want to swap the smallest with
     mov bl, [minIndex] ; get the index of the smallest number found
     mov cl, [list + eax] ; value of startIndex in list
     mov dl, [list + ebx] ; value of minIndex in list
     mov [list + eax], dl
     mov [list + ebx], cl ; swap both  values

; get the startIndex and increase it by 1
.increaseLoopCount:
     mov al, [startIndex]
     mov bl, 1
     add al, bl
     mov [startIndex], al

; set the minIndex to the last value because we need something that not already in the sorted part of the list to compare
.resetMinIndexAndLoop:
     sub edi, 1 ; edi is the index of -1 we want the index of the last one before
     mov [minIndex], edi
     jmp _start

_interrupt:
    mov rax, 60 ; system call number for exit code
    syscall
