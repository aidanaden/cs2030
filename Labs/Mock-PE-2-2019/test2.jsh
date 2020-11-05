/open Parser.java
List lines = Arrays.asList(new String[]{"one", "two three", ""})
Parser.parse(lines).linecount()
Parser.parse(lines).wordcount()
Parser.parse(lines).linecount().wordcount()
Parser.parse(lines).wordcount().linecount()