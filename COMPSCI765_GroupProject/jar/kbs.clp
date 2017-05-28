(defrule flu 
(disease-is flu)
=>
(printout t cough" "sneeze" "fever crlf))

(defrule cold 
(disease-is cold)
=>
(printout t cough" "nose" "fever crlf))

(defrule cough 
(symptom-is cough)
=>
(printout t flu" "cold" " crlf))

(defrule sneeze 
(symptom-is sneeze)
=>
(printout t flu" " crlf))

(defrule fever 
(symptom-is fever)
=>
(printout t flu" "cold" " crlf))

(defrule nose 
(symptom-is nose)
=>
(printout t cold" " crlf))

