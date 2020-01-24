# Assignment 1: Data Structures and Algorithms (CCCS 315)

![N|](https://www.mcgill.ca/research-jobs/sites/all/themes/moriarty/images/logo-red.svg)
![N|](https://cdn.icon-icons.com/icons2/1875/PNG/64/deploy_120090.png)

Professor: Jimmy Li
Student: Lucas Napoli
Development: new Class: CensoredPrintWriter extended from PrintWriter.

# Running the class
### Package
Update the package in the class: CensoredPrintWriter

```sh
package com.censored.lucas;
```

# Features!

  - Censored list of words by '*'
  - Censor words in a .txt document

You can also:
  - Select the property: public Boolean positionCensored to censored by position %d or by word. By default is false.
  ```sh
    public Boolean positionCensored = false;
```    

## Development

### Override Methods
```sh
    public PrintWriter printf(String format, Object... args) {} 
    public void println(String x) {}
    public void println(int x) {}
    public PrintWriter format(Locale l, String format, Object... args) {}
    public PrintWriter append(char c) {}
    public void write(int c) {}
    public void print(char c) {}
    public void print(char[] s) {}
    public PrintWriter append(CharSequence csq) {}
```    
### InClassMethod

```sh
    public void clientValidator () {}
    public String starCreator (String x) {}
```
### Contructors
```sh
    public String[] getCensorTerms() { return censorTerms; }
    public void setCensorTerms(String [] censorTerms) {}
    public String getClient() {}
    public void setClient(String client) {}
    public CensoredPrintWriter(String fileName) throws FileNotFoundException {}
    public Boolean getPositionCensored() {}
```


License
----

MIT


**Free Software, Hell Yeah!**

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)