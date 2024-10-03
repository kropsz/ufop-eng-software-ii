-- Inserir uma NFE
INSERT INTO tb_nfe (id, value, date, store, isUsed, payment_method)
VALUES (
           '3f48d28a-7d3e-4d8c-bf6a-42c0b8a3f6d9',  -- UUID gerado ou use 'uuid_generate_v4()' no PostgreSQL
           500.00,                                   -- valor da NFE
           '2024-09-19 15:30:00',                    -- data da NFE
           1,                                        -- código da loja
           false,                                     -- se é válida ou não
           'CREDIT_CARD'                             -- método de pagamento
       );

-- Inserir produtos associados à NFE
INSERT INTO tb_nfe_products (nfe_id, product_value, product_name)
VALUES
    ('3f48d28a-7d3e-4d8c-bf6a-42c0b8a3f6d9', 100.00, 'Produto A'),
    ('3f48d28a-7d3e-4d8c-bf6a-42c0b8a3f6d9', 150.00, 'Produto B'),
    ('3f48d28a-7d3e-4d8c-bf6a-42c0b8a3f6d9', 250.00, 'Produto C');

