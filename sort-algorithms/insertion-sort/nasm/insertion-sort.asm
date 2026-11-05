section .bss

section .data
    list dd 5, 2, 9, 4, 3, 7, -1
    unsortedIndex dd 1 ; The first index of the array is always sorted
section .text
    global _start

_start:
    mov edi, 0 ; Start searching by 0.. unsortedIndex

; Find the place of the element at [sortedListLength] in the unsorted list
_findPlaceToInsert:
    mov eax, [list + edi * 4] ; Get the element at edi
    mov ebx, [unsortedIndex] ; Get the unsorted index
    cmp [list + ebx * 4], eax ; Compare the unsortedElement with the element at edi in the sorted list
    js _setupListShift ; If the element is smaller then the element in the sorted list we found our index and can jump out
    cmp edi, [unsortedIndex]
    je _setupListShift ; If we exceed the length of the sorted list, know, that the unsorted element is already at the right place
    add edi, 1
    jmp _findPlaceToInsert ; Loop increasing edi

; Set edi to the unsorted index, to shift the list by moving backwards
_setupListShift:
    mov edx, edi ; Place of the element found in _findPlaceToInsert
    mov edi, [unsortedIndex] ; Start by the unsorted element as edi and replace it with edi - 1 to shift the list on to the right
    mov ecx, [list + edi * 4]

; Start shifting the list by setting edi to edi - 1
_shiftList:
    cmp  edi, edx ; edx is the place we want to insert the unsorted element
    je _insertUnsorted ; Shift completed if we reached the index we want to insert the new element
    mov eax, [list + edi * 4 - 4] ; Get the element at edi - 1
    mov [list + edi * 4], eax ; Set current element to the element at edi - 1
    sub edi, 1 ; Start next loop round
    jmp _shiftList

; Insert the unsorted element in the newly created empty space for the unsorted element
_insertUnsorted:
    mov [list + edx * 4], ecx ; Now we have space and can insert the unsorted element stored in ecx ( see line 27 in _setupListShift )
    mov eax, [unsortedIndex]
    add eax, 1
    mov ebx, [list + eax * 4]
    cmp ebx, -1 ; Check if the next index is inside the bounds of the list
    je _interrupt
    mov [unsortedIndex], eax ; The sorted list has increased as we inserted a new element
    jmp _start

_interrupt:
    mov rax, 60
    syscall


