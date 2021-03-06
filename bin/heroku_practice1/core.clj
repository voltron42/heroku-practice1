(ns heroku-practice1.core
  (:require [org.httpkit.server :as http]
            [clojure.xml :as x]
            [environ.core :refer [env]]))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))
        app #(-> {:status 200 
                  :headers "text/html"
                  :body (x/emit-element {:tag :html
                                         :content [{:tag :body
                                                    :content [{:tag :h2
                                                               :content ["Hello World!"]}
                                                              {:tag :p
                                                               :content ["This is a practice app!"]}]}]})})]
    (http/run-server #'app {:port port :join? false})))