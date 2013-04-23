# clj-det-enc

clj-det-enc is a encoding detector using [juniversalchardet](http://code.google.com/p/juniversalchardet/) java library.

## Usage
    (require '[det-enc.core :as det])

Usage: **(det/detect target)**

```clojure
(det/detect "utf8.txt")
;=> "UTF-8"
(det/detect "unknown.txt")
;=> nil
```

Usage: **(det/detect target encodingname-when-unknown)**

```clojure
(det/detect "unknown.txt" "EUC-JP")
;=> "EUC-JP"
(det/detect "unknown.txt" :default)
;=> "SHIFT_JIS"
```

**return:**<br>
   encoding name or nil when target encoding cannot be detected.<br>
**target:**<br>
   Whatever clojure.java.io/input-stream can deal with.<br>
   (File, filename(String), InputStream, BufferedStream etc)<br>
   Target stream is closed automatically.<br>
**encodingname-when-unknown:**<br>
   Return this value when target encoding cannot be detected.<br>
  -  **:default** means the default charset of your Java virtual machine.<br>

  
What encodings can be detected?
See [juniversalchardet](http://code.google.com/p/juniversalchardet/)

## Installation

leiningen

```clojure
[clj-det-enc "1.0.0"]
```