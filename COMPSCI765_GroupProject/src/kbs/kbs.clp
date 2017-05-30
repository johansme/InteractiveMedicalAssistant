(defrule new 
(disease-is new)
=>
(printout t symp1" "symp2" "symp3" " crlf))

(defrule new2 
(disease-is new2)
=>
(printout t sym1" "symp2" "symp3" " crlf))

(defrule symp1 
(symptom-is symp1)
=>
(printout t new" " crlf))

(defrule symp2 
(symptom-is symp2)
=>
(printout t new" "new2" " crlf))

(defrule symp3 
(symptom-is symp3)
=>
(printout t new" "new2" " crlf))

(defrule sym1 
(symptom-is sym1)
=>
(printout t new2" " crlf))

