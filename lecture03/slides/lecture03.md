## Java As A Second Language
### Lecture 03
### Collections, Generics, Exceptions

---
# Agenda
- Exceptions
- Collections and Generics
- HashMap
- Stream API

# Collections and Generics

## List

## Generic list

## Generic terms

## Generic are compile-time

## Generics vs C++ Templates

## Generics are invariant, arrays are covariant

## Generics are compile-time, arrays are reified
Reified types are those that available at runtime  
[JLS 4.7](https://docs.oracle.com/javase/specs/jls/se14/html/jls-4.html#jls-4.7)

## Type erasure
Generics only hold on compile-time (non reified)  
That means that your code cannot know the Class of generic from code  
Why?  
[For backward compatibility with programs written for Java<5](https://docs.oracle.com/javase/specs/jls/se14/html/jls-4.html#jls-4.7)


## Lists vs Arrays
Arrays are covariant
if Sub is a subtype of Super,
then the array type Sub[] is a subtype of the array type Super[]
Generics are invariant: for any two distinct types Type1 and Type2, List<Type1>
is neither a subtype nor a supertype of List<Type2>

## Map

## HashMap

## Streams

## TIL
