# statement

## Overview
Movies are collected from different data providers. Data providers don't always use the same information to describe 
the same movie. For example, the list of actors may be incomplete. The director may be missing. 
The movie length may change slightly. The release year may vary according to the region.

This tool searches in a tsv input file, containing movies data, the duplicates ones. 

## Build

Requirements
* Maven 3+
* JDK 11

To compile and run tests:
````shell script
mvn clean verify
````

## Input file format
The first line contains movie attribute name. Then we have the movie list.
Each field is separated by a tab **\t** and line is separated by **\n**.
Unknown field is presented as **\N**. For example: 
```
id	year	length	genre	directors	actors
tt2355936	2013	89	Drama	Lina Chamie	Lucas Zamberlan,Gregório Mussatti Cesare,Dira Paes,Julia Weiss,Antônia Ricca,Marco Ricca
tt0226204	1999	95	Drama,Sci-Fi	Kazuya Konaka	Timothy Breese Miller,J. David Brimmer,Jessica Calvello,Shannon Conley
tt1226780	2008	92	Horror	Shawn Cain	Chuck Williams,Neil D'Monte,Larry Laverty,Stefano Capone
tt0109288	1994	92	Action,Comedy,Crime	Mike Binder	Damon Wayans,David Alan Grier,Robin Givens,Christopher Lawford
tt0444519	2006	90	Comedy,Sci-Fi	Eric Lartigau	Kad Merad,Olivier Baroux,Marina Foïs,Guillaume Canet
tt1847669	2011	103	Documentary	Thunska Pansittivorakul	\N
tt5127394	2015	107	Animation,Fantasy,Sci-Fi	Paris Tosen	\N
```

A movie has the following attributes: 
- id : unique in the provided list 
- year: release year
- length: running duration in minutes
- genre: zero, one or many categories
- actors: zero, one or many actors
- directors: zero, one or many directors


## Output
The tool output result is in the file `duplicates.txt`.
Each line contains movies Ids that had been found as duplicated.

```
f7b5b8cb-293b-4ced-ba19-e5b781935489    a86cbd11-5eed-41e6-9da7-42bf3eb1c99a 
```

## Duplication rules

For two entries describing the same movie, it's possible to have the following differences:
- id: each id is unique
- year: 1 year more or less at maximum 
- length: 5% more or less at maximum, 2 * |x - y|/(x + y) <= 0.05 where x and y is the provided movie length
- genre: different order, or missing one or many genres 
- actors: different order, or missing one or many actors
- directors: different order, or missing one or many directors

Take the two following lines for example, they both refer to the same movie but have different information:
```
id	year	length	genre	directors	actors
f7b5b8cb-293b-4ced-ba19-e5b781935489    2007    60      Horror  Jamie Sharps    Alan Gilman,Heather Hamilton,Lisa Lovett,Reggie Provencher,Jareth Ryan
a86cbd11-5eed-41e6-9da7-42bf3eb1c99a    2008    63      Horror  \N      Jareth Ryan,Reggie Provencher,Alan Gilman,Heather Hamilton,Lisa Lovett
```

## Input
The first line contains movie attribute name. Then we have the movie list.
Each field is separated by a tab **\t** and line is separated by **\n**.
Unknown field is presented as **\N**. For example: 
```
id	year	length	genre	directors	actors
tt2355936	2013	89	Drama	Lina Chamie	Lucas Zamberlan,Gregório Mussatti Cesare,Dira Paes,Julia Weiss,Antônia Ricca,Marco Ricca
tt0226204	1999	95	Drama,Sci-Fi	Kazuya Konaka	Timothy Breese Miller,J. David Brimmer,Jessica Calvello,Shannon Conley
tt1226780	2008	92	Horror	Shawn Cain	Chuck Williams,Neil D'Monte,Larry Laverty,Stefano Capone
tt0109288	1994	92	Action,Comedy,Crime	Mike Binder	Damon Wayans,David Alan Grier,Robin Givens,Christopher Lawford
tt0444519	2006	90	Comedy,Sci-Fi	Eric Lartigau	Kad Merad,Olivier Baroux,Marina Foïs,Guillaume Canet
tt1847669	2011	103	Documentary	Thunska Pansittivorakul	\N
tt5127394	2015	107	Animation,Fantasy,Sci-Fi	Paris Tosen	\N
```

## How to run
You first have to clone this repository and then build the tool:
```
mvn clean verify
```
Once build, you can launch it:

```
java -jar target/statement.jar YOUR_INPUT_FILE_CANONICAL_PATH 
```