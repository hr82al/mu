set @from = '10.168.150.74', @to = '192.168.1.215';
update hmmr_mu.hmmr_activity_type set Icon = REPLACE(Icon, @from, @to) where id > 0;
update hmmr_mu.hmmr_mu_prior set Icon = REPLACE(Icon, @from, @to)  where id > 0;

update hmmr_mu.hmmr_work_plan
set
	Instruction = REPLACE(Instruction, '10.168.150.74', '192.168.1.215') where
Instruction like '%10.168.150.74%' and id > 0;

2, 4, 5, 6