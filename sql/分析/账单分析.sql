SELECT DATE_FORMAT(accept_time,'%Y%u') weeks,SUM(amount) FROM invest_turnover WHERE STATUS='SUCCESS' AND `type` LIKE '%RECHARGE%' GROUP BY weeks;-- 按照周分组
SELECT DATE_FORMAT(accept_time,'%Y%m%d') days,SUM(amount) FROM invest_turnover WHERE STATUS='SUCCESS' AND `type` LIKE '%RECHARGE%' GROUP BY days;-- 按照日分组
SELECT DATE_FORMAT(accept_time,'%Y%m') months,SUM(amount) FROM invest_turnover WHERE STATUS='SUCCESS' AND `type` LIKE '%RECHARGE%' GROUP BY months;-- 按照月分组
SELECT DATE_FORMAT(accept_time,'%Y') years,SUM(amount) FROM invest_turnover WHERE STATUS='SUCCESS' AND `type` LIKE '%RECHARGE%' GROUP BY years;-- 按照月分组




SELECT type,type_name,SUM(amount) FROM `invest_turnover` it WHERE STATUS='SUCCESS' AND `type` LIKE '%RECHARGE%' GROUP BY type,type_name UNION
SELECT type,type_name,SUM(amount) FROM `invest_turnover` it WHERE STATUS='SUCCESS' AND `type` LIKE '%WITHDRAW%' GROUP BY type,type_name;
SELECT type,type_name,SUM(amount) FROM `invest_turnover` it WHERE STATUS='SUCCESS' GROUP BY type,type_name