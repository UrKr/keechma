(ns keechma.test.core
  (:require [doo.runner :refer-macros [doo-tests]]
            [cljs.test :as test]
            [keechma.test.app-state]
            [keechma.test.controller]
            [keechma.test.controller-manager]
            [keechma.test.ui-component]))

(enable-console-print!)

(doo-tests 'keechma.test.app-state
           'keechma.test.controller
           'keechma.test.controller-manager
           'keechma.test.ui-component
          )
