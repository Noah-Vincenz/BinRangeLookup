# BinRangeLookup
Code to look up BIN ranges

A payment card number can be classified by its first several digits, a.k.a. the prefix. Here's a data set that classifies card numbers by their prefix. Each item is said to classify a card if that card's prefix lands within the inclusive range of `[prefixRangeStart, prefixRangeEnd]`. For example, the card `6705 6700 0000 0000` is classified as a US debit card.
```
[
  { "prefixRangeStart": "6706", ... }
  { "prefixRangeStart": "68012200", ... }
  { "prefixRangeStart": "670500", ... }
  { "prefixRangeStart": "68012310", ... }
]
```
The catch is that we can't give you the full 16-digit card number to classify. Instead, we can only provide the exact first 6 digits of the card, a.k.a. the BIN. So for our previous example, the BIN for the card `6705 6700 0000 0000` is `670567`.

Write a function that takes a data set like the one above and a card BIN, and returns all items in the data set that could classify the card BIN. For example, your function should return just the third item in the data set for the card BIN `670567`.

We'd like to see correctness, testing, code structure, and performance in that order. So try to focus first on getting a solution that correctly handles all the classification data before following up with tidier or faster code.