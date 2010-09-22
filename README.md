# detect-encoding-clj

Detect-encoding-clj is a encoding detector using [juniversalchardet](http://code.google.com/p/juniversalchardet/) java library.

## Usage
    (use 'detect-encoding-clj.core)

Usage: **(detect target)**

    (detect "unknownA.txt")
     => "UTF-8"
    (detect "unknownB.txt")
     => nil

Usage: **(detect target encodingname-when-unknown)**

    (detect "unknownB.txt" "EUC-JP")
    => "EUC-JP"
    (detect "unknownB.txt" :default)
    => "SHIFT_JIS"

**return:**<br>
   encoding name or nil when target encoding cannot be detected.<br>
**target:**<br>
   Whatever clojure.java.io/input-stream can deal with.<br>
   (File, filename(String), InputStream, BufferedStream etc)<br>
   Target stream is closed automatically.<br>
**encodingname-when-unknown:**<br>
   Return this value when target encoding cannot be detected.<br>
  -  **:default** means the default charset of this Java virtual machine.
<br>
What encodings can be detected?
See [juniversalchardet](http://code.google.com/p/juniversalchardet/)

## Installation

leiningen

    [detect-encoding-clj "1.0.0-SNAPSHOT"]
