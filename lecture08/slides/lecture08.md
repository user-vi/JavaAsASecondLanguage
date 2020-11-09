## Java As A Second Language
### Lecture 08
### Garbage Collection

---
## Agenda
- Practice 1 - Fixing Billing Service

---
## What if we just write concurrent program as single-threaded?
**Many things will go wrong**
> @see billing


---
## Billing example
This simple example shows how you can loose money when using bad synchronization Oo  
Billing service allow to transfer money between users  
```bash
curl -XPOST localhost:8080/billing/addUser -d "user=sasha&money=100000"
curl -XPOST localhost:8080/billing/addUser -d "user=sergey&money=100000"
curl localhost:8080/billing/stat
curl -XPOST localhost:8080/billing/sendMoney -d "from=sergey&to=sasha&money=1"
```

---
## Billing example
Start server and emulate fast money transfer with **jmeter**. Then look at stat again:
```bash
curl localhost:8080/billing/stat
```
> @see billing

---
## You've lost your money (or gained)
Technically, the invariant was broken:
> Total amount of money in system must be preserved during **sendMoney**

---
## Why we loose money?  
Because in multithreaded systems **guarantees are weaker** than in single-threaded.  
Multithreaded systems **without proper synchronization** have some problems.

---
## Race condition
Race condition (состояние гони, гонка)
program behaviour where the output is dependent on the
sequence or timing of other uncontrollable events  
  
Behaviour of multithreaded program is (inter alia) dependent on **OS scheduling**  
  
**Uncontrollable races are almost always erroneous**  

---
## Data races
**Data race** - when several processes communicate via **shared mutable state** and at least one is writing **without proper synchronization**  
 (Not the same as race conditions)

---
## TIL
- volatile
- ConcurrentHashMap
- BlockingQueue
- Atomic
- If you write concurrent programs like single-threaded you can get into trouble