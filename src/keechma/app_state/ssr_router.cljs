(ns keechma.app-state.ssr-router
  (:require [keechma.app-state.core :as core :refer [IRouter]]
            [router.core :as router]
            [keechma.app-state.history-router :as history-router]
            [cljs.core.async :refer [put!]]))

(defrecord SsrRouter [current-url routes routes-chan base-href app-db]
  IRouter
  (start! [this]
    (swap! app-db assoc :route (router/url->map routes current-url))
    this)
  (wrap-component [this]
    (fn [& children]
      (into [:div {:on-click (fn [_])}]
            children)))
  (url [this params]
    (str base-href (router/map->url routes params))))

(defn constructor [current-url routes routes-chan state]
  (let [base-href (history-router/process-base-href (or (:base-href state) "/"))]
    (core/start! (->SsrRouter current-url (router/expand-routes routes) routes-chan base-href (:app-db state)))))
