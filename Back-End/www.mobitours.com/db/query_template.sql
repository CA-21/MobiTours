-- load the rating sum of a poi from rating table and Hotel table
SELECT  sum(c.rating) as rate,h.title as title 
    FROM hotel h JOIN rating c ON h.pointofinterestid=c.pointofinterestid
    GROUP BY h.title


