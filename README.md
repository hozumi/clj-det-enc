# detect-encoding-clj

Detect-encoding-clj is a encoding detector using juniversalchardet.

## Usage
    (use 'detect-encoding-clj.core)

Usage: (detect target)

    (detect "unknownA.txt")
     => "UTF-8"
    (detect "unknownB.txt")
     => nil

Usage: (detect target encodingname-when-unknown)

   (detect "unknownB.txt" "EUC-JP")
   => "EUC-JP"
   (detect "unknownB.txt" :default)
   => "SHIFT_JIS"

return : encoding name or nil when target encoding cannot be detected.
target : Whatever clojure.java.io/input-stream can deal with.
         (File, filename(String), InputStream, BufferedStream etc)
         Target stream is closed automatically.
encodingname-when-unknown :
         Return this value when target encoding cannot be detected.

         :default means the default charset of this Java virtual machine.

	 
What encodings can be detected?
See http://code.google.com/p/juniversalchardet/

## Installation

leiningen

    [detect-encoding-clj "1.0.0-SNAPSHOT"]
