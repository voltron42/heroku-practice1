(ns heroku-practice1.core
  (:require [org.httpkit.server :refer:all]))

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    (@server :timeout 100)
    (reset! server nil)))

(defn -main [& args]
  (let [app #(-> {:status 200 
                  :headers "text/html"
                  :body (emit-element {:tag :html
                                       :content [{:tag :body
                                                  :content [{:tag :h2
                                                             :content ["Hello World!"]}
                                                            {:tag :p
                                                             :content ["This is a practice app!"]}]}]})})]
    (reset! server (run-server #'app {:port 5000}))))