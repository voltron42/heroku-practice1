(ns heroku-practice1.core
  (:require [org.httpkit.server :as http]
            [clojure.xml :as x]
            [environ.core :refer [env]]))

(def my-app (constantly {:status 200
                         :headers "text/html"
                         :body (with-out-str
                                 (x/emit-element {:tag :html
                                                  :content [{:tag :body
                                                             :content [{:tag :h2
                                                                        :content ["Hello World!"]}
                                                                       {:tag :p
                                                                        :content ["This is a practice app!"]}]}]}))}))

(defn -main [& [port]]
  (let [port (Integer. ^int (or port (env :port) 5000))]
    (http/run-server #'my-app {:port port :join? false})))