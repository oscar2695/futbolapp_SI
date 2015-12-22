use footballgames;

insert into stadium (capacity,name) values (99354,"Camp Nou");
insert into stadium (capacity,name) values (81044,"Santiago Bernabéu");
insert into stadium (capacity,name) values (55000,"Mestalla");
insert into stadium (capacity,name) values (54851,"Vicente Calderón");
insert into stadium (capacity,name) values (53289,"San Mamés");
insert into stadium (capacity,name) values (41700,"Sánchez Pizjuán");
insert into stadium (capacity,name) values (40500,"Power8");
insert into stadium (capacity,name) values (36017,"Martínez Valero");
insert into stadium (capacity,name) values (33091,"Riazor");
insert into stadium (capacity,name) values (32076,"Anoeta");
insert into stadium (capacity,name) values (30044,"La Rosaleda");
insert into stadium (capacity,name) values (29000,"Balaídos");
insert into stadium (capacity,name) values (24500,"El Madrigal");
insert into stadium (capacity,name) values (22524,"Nuevo Los Cármenes");
insert into stadium (capacity,name) values (22000,"Juegos del Mediterráneo");
insert into stadium (capacity,name) values (21822,"Nuevo Arcángel");
insert into stadium (capacity,name) values (17000,"Coliseum Alfonso Pérez");
insert into stadium (capacity,name) values (14708,"Estadio de Vallecas");
insert into stadium (capacity,name) values (25354,"Ciutat de Valencia");
insert into stadium (capacity,name) values (5250,"Ipurúa");

insert into team (name,stadium_id) values ("Real Madrid",2);
insert into team (name,stadium_id) values ("FC Barcelona",1);
insert into team (name,stadium_id) values ("At. Madrid",4);
insert into team (name,stadium_id) values ("Sevilla",6);
insert into team (name,stadium_id) values ("Athletic",5);
insert into team (name,stadium_id) values ("Valencia",3);
insert into team (name,stadium_id) values ("Deportivo",9);
insert into team (name,stadium_id) values ("Espanyol",7);
insert into team (name,stadium_id) values ("Real Sociedad",10);
insert into team (name,stadium_id) values ("Getafe",17);
insert into team (name,stadium_id) values ("Elche",8);
insert into team (name,stadium_id) values ("Villarreal",13);
insert into team (name,stadium_id) values ("Córdoba",16);
insert into team (name,stadium_id) values ("Málaga",11);
insert into team (name,stadium_id) values ("Granada",14);
insert into team (name,stadium_id) values ("Almería",15);
insert into team (name,stadium_id) values ("Eibar",20);
insert into team (name,stadium_id) values ("Celta",12);
insert into team (name,stadium_id) values ("Levante",19);
insert into team (name,stadium_id) values ("Rayo",18);


insert into match_table (teamLocal_id,teamVisitor_id,goalsLocal,goalsVisitor,dateMatch,spectators) values (14,5,1,0,'2014-08-23',24500);
insert into match_table (teamLocal_id,teamVisitor_id,goalsLocal,goalsVisitor,dateMatch,spectators) values (4,6,1,1,'2014-08-23',29352);
insert into match_table (teamLocal_id,teamVisitor_id,goalsLocal,goalsVisitor,dateMatch,spectators) values (15,7,2,1,'2014-08-23',14800);
insert into match_table (teamLocal_id,teamVisitor_id,goalsLocal,goalsVisitor,dateMatch,spectators) values (16,8,1,1,'2014-08-23',12000);
insert into match_table (teamLocal_id,teamVisitor_id,goalsLocal,goalsVisitor,dateMatch,spectators) values (17,9,1,0,'2014-08-23',5173);
insert into match_table (teamLocal_id,teamVisitor_id,goalsLocal,goalsVisitor,dateMatch,spectators) values (18,10,3,1,'2014-08-23',20416);
insert into match_table (teamLocal_id,teamVisitor_id,goalsLocal,goalsVisitor,dateMatch,spectators) values (2,11,3,0,'2014-08-23',68105);
insert into match_table (teamLocal_id,teamVisitor_id,goalsLocal,goalsVisitor,dateMatch,spectators) values (19,12,0,2,'2014-08-23',19000);
insert into match_table (teamLocal_id,teamVisitor_id,goalsLocal,goalsVisitor,dateMatch,spectators) values (1,13,2,0,'2014-08-23',70356);
insert into match_table (teamLocal_id,teamVisitor_id,goalsLocal,goalsVisitor,dateMatch,spectators) values (20,3,0,0,'2014-08-23',11070);


insert into `footballgames`.`user` (username,password) values ("admin","pass");
insert into `footballgames`.`user` (username,password) values ("user","pass");