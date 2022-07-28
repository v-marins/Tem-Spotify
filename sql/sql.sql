create database temspotify;
create user 'temspotify'@'localhost' identified by 'T3m$p0tiFy';
grant all privileges on temspotify.* to 'temspotify'@'localhost';
use temspotify;

create table tblmusica(
idMusica int not null auto_increment,
artista varchar(100),
album varchar(100),
estilo int,
linkMP3 text,
constraint pk_musica primary key(idMusica)
);

create table tblusuario(
idUsuario int not null auto_increment,
nome varchar(100),
email varchar(100),
senha varchar(100),
constraint pk_usuario primary key(idUsuario)
);

create table tblplaylist(
idPlaylist int not null auto_increment,
titulo varchar (100),
idUsuario int not null,
constraint pk_playlist primary key (idPlaylist),
constraint fk_usuario foreign key (idUsuario) references tblusuario(idUsuario)
);

create table tblmusicaplaylist(
idMusica int not null,
idPlaylist int not null,
constraint pk_mp primary key (idPlaylist,idMusica),
constraint fk_mu foreign key (idMusica) references tblmusica(idMusica),
constraint fk_pl foreign key (idPlaylist) references tblplaylist(idPlaylist)
);

