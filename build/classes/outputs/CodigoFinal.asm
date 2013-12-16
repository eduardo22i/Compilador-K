    .data
_s:   .word 
_p:   .word 
_sa:   .word 
_sap:   .word 
_saw2:   .word 
_s2:   .word 
_op:   .word 
_saw:   .word 
_op:   .word 
_saw:   .word 
_q:   .word 
_p:   .word 
_msg0:    .asciiz "holaaaaaa"
_msg1:    .asciiz "%f"
_msg2:    .asciiz "hola que pedos"
_msg3:    .asciiz "funciona super bien el print"
_msg4:    .asciiz "este es el final"


    .text
    .globl _main

_s: 
lw $t0, sa
li $t1, 4
blt  $t0 $t1, _etiq
b _etiq
li $t2,2
add $t2, $t2, 2
li $v0, 2
la $a0, _sap
syscall
li $v0, 5
syscall
li $v0, 4
la $a0, _msg2
syscall
li $t3,4
mult $t3, $t3, 7
li $t4,3
div $t4, $t4, $t3
lw $t5, _sa
add $t3, $t5, $t4
li $t4,2
div $t4, $t4, 3
add $t5, $t4, 4
lw $t4, op
lw $t6, 3.2
beq  $t4 $t6, _etiq
b _etiq
li $v0, 4
la $a0, _msg3
syscall
li $t4,2
add $t4, $t4, 2
li $v0, 2
move $sp,$fp
lw $fp,-4($sp)
lw $ra,-8($sp)
jr $ra

_main: 
lw $t6, op
lw $t7, 3.2
bne  $t6 $t7, _etiq
b _etiq
li $t6,2
add $t6, $t6, 2
li $v0, 4
la $a0, _msg4
syscall
lw $a0, _q
lw $a1, _p
jal _s
li $v0, 0
move $sp,$fp
lw $fp,-4($sp)
lw $ra,-8($sp)
jr $ra
li $v0,10
syscall
