## Java As A Second Language
### Lecture 09
### GC, Memory layout

---
## Agenda
- Byte buddy (continue)
- Java heap
- Basics of Java GC

---
## Object creation
**new** keyword is the moment of allocation
```java
new Object();//new object will be created here
```
---
## Heap structure
Global structure of java process is defined in Java Virtual Machine Specification (JVMS)
https://docs.oracle.com/javase/specs/jvms/se14/html/jvms-2.html#jvms-2.5  
<img src="img/heap.png" alt="heap" style="width: 600px;"/>  

---
## Object layout
Use JOL to learn about object layout
@see practice2
Compressed OOPS - only 4 bytes for references for small heaps.

Fun read  
https://shipilev.net/jvm/objects-inside-out/  
https://shipilev.net/jvm/anatomy-quarks/23-compressed-references/
---
## Object removal
- Object is removed when there are no hard references pointing at it
- Or any time later...
- This is controlled by GC
- At the moment of GC Object.finalize() method is invoked
- you can not control GC directly from Runtime
- even with Runtime.gc() - it's only an advice

**Never rely on Object.finalize() method**

---
## Basics of GC
Heap is automatically managed by Garbage Collector  
The structure of heap depend on chosen GC implementation  
**Hotspot** provide several implementations  

---
# GC Q&A
Q: Why we need Garbage Collector  
A: To allocate, to remove, to defragment heap  
Q: What is garbage?  
A: Objects that are no longer referenced  
Q: Does cyclic references stall objects in heap forever  
A: No. Object is garbage if it can not be reached from GC Roots  
Q: What are GC Roots? A: ... (proceed reading)  

---
## GC Roots
<img src="img/gcroots.jpg" alt="gcroots" style="width: 700px;"/>

---
## GC is configurable
There are several implementations of GC in Hotspot. Each of them is configurable via **JVM parameters**.
<img src="img/gcs.png" alt="monitor" style="width: 700px;"/>  

---
## GC configuration with jvm parameters
See simple logs for GC  
-verbose:gc   

Us this flag to get heap dump when OOM happens for further investigation  
-XX:+HeapDumpOnOutOfMemoryError  

Configure used GC (g1 is default in most cases)    
-XX:+UseSerialGC 

Configure heap size  
-Xms1024m -Xmx1024m  

---
## Let's try to understand what GC does
@See practice1

---
## Is GC a silver bullet?
GC power comes at cost of **performance**:
1. GC is one or more threads working in background
2. All gc implementations in Hotspot are **stop the world** (sometimes during GC work all application threads stop running until gc done)

---
## Stop the world
All GCs in HotSpot are **Stop-the-world**
i.e. there are moments when all the application threads are stopped and GC is working.  
Different GCs implement different strategies to reduce pauses. Some even give guarantees of maximum pause time.  
There is an attempt to implement ‘ultra-low pause’ GC  
http://openjdk.java.net/projects/shenandoah/ (not production-ready)  
There are JVM implementations where GC is pauseless: https://www.azul.com/products/zing/ (proprietary)  

---
## Fun read
https://shipilev.net/jvm/diy-gc/

---
## TIL
- Byte Buddy is fun
- GC simplifies memory management for you
- you can not control gc directly
- you can choose GC and configure it
- You can understand how object is allocated in memory using JOL tool