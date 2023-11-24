--gerando usuário padrao com a senha 123
INSERT INTO users(name,
				  email,
				  password,
				  birthday,
				  date_create)
	VALUES ('Erick Marques',
			'marques.erick@outlook.com',
			'202cb962ac59075b964b07152d234b70',
			'1994-05-09',
			now());