(deftemplate person
    (multislot surname)(slot firstname)
)

(deffacts initial
(person (surname Ban)(firstname James))
(person (surname Kumar)(firstname Sumeet))
(person (surname Meyer)(firstname Johannes))
(person (surname Ni)(firstname Lin))
(person (surname Prasad)(firstname Vikrant)))

(defrule find_James
    ?person<-(person(firstname James))
    =>
    (printout t ?person crlf)
)
(defrule find_Sumeet
    ?person<-(person(firstname Sumeet))
    =>
    (printout t ?person crlf)
)
(defrule find_Johannes
    ?person<-(person(firstname Johannes))
    =>
    (printout t ?person crlf)
)
(defrule find_Lin
    ?person<-(person(firstname Lin))
    =>
    (printout t ?person crlf)
)
(defrule find_Vikrant
    ?person<-(person(firstname Vikrant))
    =>
    (printout t ?person crlf)
)
