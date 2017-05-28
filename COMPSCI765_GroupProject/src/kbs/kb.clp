(deftemplate disease "three symptom disease"
	(slot dname)
	(slot symptom1)
	(slot symptom2)
	(slot symptom3))
	
(deffacts initial-facts "initial facts"
	(disease(dname flu)(symptom1 cough)(symptom2 sneeze)(symptom3 fever))
	(disease(dname cold)(symptom1 cough)(symptom2 nose)(symptom3 fever)))
	
(defrule getdiag "obtain disease"
	(disease(symptom1 cough))
=>
	(printout t "Disease is" crlf))
	
(defrule one "test"
    (animal-is duck)
=>
    (printout t "quack" crlf))
    