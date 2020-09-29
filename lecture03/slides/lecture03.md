## Java As A Second Language
### Lecture 03
### Collections, Generics, Exceptions

---
# Agenda
- Collections
- List
- Generics
- Set
- Map
- Stream API

---
# Collections
Java collections zoo
<img src="Collections.png" alt="collections"/>
---


# Agenda
- Collections
- **[List]**
- Generics
- Set
- Map
- Stream API
---

## List
Auto-resizeable array
Resizes x1.5 times per increase
---


## ArrayList. Complexity

|  contains  | add   | get   |  set  | remove | 
|:----------:|:-----:|:-----:|:-----:|:------:|
| O(n)       | O(1)* |  O(1) |  O(1) | O(n)   |

* Amortized complexity. How to implement List that has O(1)?
---
## Generics
---
# Agenda
- Collections
- List
- **[Generics]**
- Set
- Map
- Stream API

## Generic list
```java
var intList = new List<Integer>();
List<String> strList = new ArrayList<>();
```
---

## Generic terms

---


## Generic are compile-time
Generics only hold on compile-time (non reified)  
That means that your code cannot know the Class of generic from code  
Why?  

---
## Type Erasure
Generics were added to Java to ensure type safety and to ensure that generics wouldn't cause overhead at runtime.  
The compiler applies a process called type erasure on generics at compile time.

Type erasure removes all type parameters and replaces it with their bounds or with Object if the type parameter is unbounded. Thus the bytecode after compilation contains only normal classes, interfaces and methods thus ensuring that no new types are produced. Proper casting is applied as well to the Object type at compile time.
[For backward compatibility with programs written for Java<5](https://docs.oracle.com/javase/specs/jls/se14/html/jls-4.html#jls-4.7)
---
## Generics vs C++ Templates

---
## Generics are invariant, arrays are covariant


---
## Generics are compile-time, arrays are reified
Reified types are those that available at runtime  
[JLS 4.7](https://docs.oracle.com/javase/specs/jls/se14/html/jls-4.html#jls-4.7)
---

## Lists vs Arrays
Arrays are covariant  
if Sub is a subtype of Super,  
then the array type Sub[] is a subtype of the array type Super[]  
Generics are invariant: for any two distinct types Type1 and Type2, List<Type1>  
is neither a subtype nor a supertype of List<Type2>

---
# Agenda
- Collections
- List
- Generics
- *[Map and Set]
- Stream API
---

## HashMap

---
## Streams

---
## TIL
