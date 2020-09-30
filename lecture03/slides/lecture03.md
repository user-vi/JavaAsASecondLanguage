## Java As A Second Language
### Lecture 03
### Collections, Generics, Exceptions

---
# Agenda
- Collections
- ArrayList
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
- **[ArrayList]**
- Generics
- HashMap
- Stream API
---

## ArrayList
Auto-resizeable array

```java
var l1 = new ArrayList<String>();
var ships = List.of<String>("Boeotians", "Minyans", "Phocēans"); //unmodifiable list
```
---

## ArrayList. Internals #1

```java
List<String> list = new ArrayList<>();
```
<img src="img/newarray.png" alt="exception" style="width: 600px;"/>

```java
list.add("0");
list.add("1");
```

<img src="img/array1.png" alt="exception" style="width: 600px;"/>


---
### ArrayList. Internals #2
```java
list.addAll(Arrays.asList("2", "3", "4", "5", "6", "7", "8"));
list.add("9");
```

<img src="img/array9.png" alt="exception" style="width: 600px;"/>


---
### ArrayList. Internals #3
```java
list.add("10");
```
Not enough capacity. Need (auto)resize.

<img src="img/arrayresized.png" alt="exception" style="width: 750px;"/>

<img src="img/array10.png" alt="exception" style="width: 750px;"/>

---
### Quiz
#### What is the difference between capacity and size in `ArrayList`?

---


## ArrayList. Complexity

|  contains  | add   | get   |  set  | remove | 
|:----------:|:-----:|:-----:|:-----:|:------:|
| O(n)       | O(1)* |  O(1) |  O(1) | O(n)   |

* Amortized complexity. How to implement List that has O(1) on add()?
---
## Generics
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
