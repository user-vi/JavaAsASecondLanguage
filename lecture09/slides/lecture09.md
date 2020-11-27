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

---
## Heap structure
Global structure of java process is defined in Java Virtual Machine Specification (JVMS)
https://docs.oracle.com/javase/specs/jvms/se14/html/jvms-2.html#jvms-2.5  
<img src="img/heap.png" alt="heap" style="width: 600px;"/>  

---
## Object layout

Compressed OOPS - only 4 bytes for references for small heaps.

Useful references  
https://shipilev.net/jvm/objects-inside-out/  
https://shipilev.net/jvm/anatomy-quarks/23-compressed-references/
---
## Object removal
- This is controlled by GC
- At the moment of GC Object.finalize() method is invoked
- you can not control that directly

**Never rely on Object.finalize() method**

---
## Basics of GC
Heap is automatically managed by Garbage Collector (GC).  
The structure of heap depend on chosen GC implementation.  
**Hotspot** provide several implementations.

---
# GC Q&A
Q: Why we need Garbage Collector  
A: To remove garbage, obviously  
Q: What is garbage?  
A: Objects that are no longer referenced  
Q: Does cyclic references stall objects in heap forever  
A: No. Object is garbage if it can not be reached from GC Roots  
Q: What are GC Roots? A: ... (proceed reading)  

---
## GC is configurable
There are several implementations of GC in Hotspot. Each of them is configurable via **JVM parameters**.
<img src="img/gcs.png" alt="monitor" style="width: 700px;"/>  

---
## GC configuration
See simple logs for GC  
-verbose:gc  

Get more logs  
-XX:+PrintGCDetails  

Us this flag to get heap dump when OOM happens for further investigation  
-XX:+HeapDumpOnOutOfMemoryError  

Configure used GC (g1 is default in most cases)    
-XX:+UseSerialGC 

Configure heap size  
-Xms1024m -Xmx1024m  

---
## TIL
- Byte Buddy is fun
- GC simplifies memory management for you
- you can not control gc directly
- you can choose GC and configure it
- You can understand how object is allocated in memory using JOL tool