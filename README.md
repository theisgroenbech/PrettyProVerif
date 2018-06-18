# PrettyProVerif
Little script to make ProVerif output more readable.

As of now, it does two things:
- It will rename all instances of with long numbers after to the lowest unique number:
event(startInitiator(U_2165,V_2166,S_U_2167)) to event(startInitiator(U_1,V_1,S_U_1))
- Create an overview with the result of each query.

It is based on Java 9, but might run in other versions.

## Running

Copy the files to your ProVerif directory and run chmod +x ppv.

Then use ppv instead of proverif when running.
