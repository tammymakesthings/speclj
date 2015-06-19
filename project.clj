(defproject speclj "3.2.0"
            :description "speclj: Pronounced 'speckle', is a Behavior Driven Development framework for Clojure."
            :url "http://speclj.com"
            :license {:name         "The MIT License"
                      :url          "file://LICENSE"
                      :distribution :repo
                      :comments     "Copyright 2011-2015 Micah Martin All Rights Reserved."}

            :jar-exclusions [#"\.cljx|\.swp|\.swo|\.DS_Store"]
            :javac-options ["-target" "1.5" "-source" "1.5"]
            :source-paths ["src/clj" "src/cljs" "target/src/clj" "target/src/cljs"]
            :test-paths ["spec/clj" "target/spec/clj"]

            :dependencies [
                           [org.clojure/clojure "1.7.0-RC2"]
                           ;[org.clojure/clojure "1.6.0"]
                           [org.clojure/clojurescript "0.0-3308"]
                           [fresh "1.0.2"]
                           [mmargs "1.2.0"]
                           [trptcolin/versioneer "0.1.1"]]

            :cljx {:builds [{:source-paths ["src/cljx"]
                             :output-path  "target/src/clj"
                             :rules        :clj}
                            {:source-paths ["src/cljx"]
                             :output-path  "target/src/cljs"
                             :rules        :cljs}
                            {:source-paths ["spec/cljx"]
                             :output-path  "target/spec/clj"
                             :rules        :clj}
                            {:source-paths ["spec/cljx"]
                             :output-path  "target/spec/cljs"
                             :rules        :cljs}]}

            :java-source-paths ["src/clj"]

            :profiles {:dev {:dependencies [[com.keminglabs/cljx "0.6.0"]
                                            ;[org.clojure/clojurescript "0.0-3308"]
                                            ]
                             :plugins      [[com.keminglabs/cljx "0.6.0"]
                                            [lein-cljsbuild "1.0.6"]
                                            [codox "0.8.11"]]}}

            :cljsbuild {:builds {:dev {:source-paths   ["src/clj" "target/src/cljs" "src/cljs" "target/spec/cljs" "spec/cljs"]
                                       :compiler       {:output-to    "target/tests.js"
                                                        :pretty-print true}
                                       :notify-command ["phantomjs" "bin/specljs" "target/tests.js"]
                                       }}
                        ;:test-commands {"test" ["phantomjs" "bin/specljs" "target/tests.js"]}
                        }

            :prep-tasks [["cljx" "once"] "javac" "compile"]

            :aliases {"cljs" ["do" "clean," "cljx" "once," "cljsbuild" "once" "dev"]
                      "ci"   ["do" "clean," "javac," "spec," "cljsbuild" "once" "dev"]}

            :eval-in :leiningen                             ; to recognize spec task

            :codox {:src-dir-uri               "http://github.com/slagyr/speclj/blob/3.2.0/"
                    :src-linenum-anchor-prefix "L"}
            )
