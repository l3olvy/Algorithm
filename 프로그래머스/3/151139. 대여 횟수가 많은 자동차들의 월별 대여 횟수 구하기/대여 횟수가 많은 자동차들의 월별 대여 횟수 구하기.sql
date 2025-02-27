-- 코드를 입력하세요
SELECT month(car.START_DATE) MONTH, car.CAR_ID CAR_ID, count(*) RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY car
WHERE year(car.START_DATE) = 2022 AND month(car.START_DATE) >= 8 AND month(car.START_DATE) <= 10 AND car.CAR_ID IN (SELECT c.CAR_ID
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY c
    WHERE year(c.START_DATE) = 2022 AND month(c.START_DATE) >= 8 AND month(c.START_DATE) <= 10
    GROUP BY c.CAR_ID
    HAVING count(CAR_ID) >= 5)
GROUP BY car.CAR_ID, month(car.START_DATE)
HAVING count(*) > 0
ORDER BY month(car.START_DATE), car.CAR_ID DESC