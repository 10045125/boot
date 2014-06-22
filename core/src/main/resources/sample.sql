select team0_.id as id1_5_, team0_.description as descript2_5_, team0_.name as name3_5_
from team team0_
inner join team_team_members teammember1_ on team0_.id=teammember1_.team_id
inner join team_member teammember2_ on teammember1_.team_members_id=teammember2_.id
where teammember2_.account_id=?