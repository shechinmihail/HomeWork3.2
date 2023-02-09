ALTER TABLE student
    ALTER COLUMN age SET NOT NULL,
    ADD CONSTRAINT age_constraint CHECK (age >= 16),
ALTER COLUMN age SET DEFAULT 20,
    ADD CONSTRAINT name_constraint UNIQUE(name);


ALTER TABLE faculty
    ADD CONSTRAINT name_color_unique UNIQUE (name, color);