-- Criado manualmente
CREATE TABLE IF NOT EXISTS users_audit (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    old_password VARCHAR(255) NOT NULL,
    new_password VARCHAR(255) NOT NULL
) engine=InnoDB DEFAULT CHARSET=utf8;
