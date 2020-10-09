insert into inventory (inventory_id, inventory_name)
VALUES ('1', 'Default Store');

insert into inventory_item (item_id, item_name, item_desc, price, quantity, inventory_id)
VALUES ('1',
        'X Ear Phone',
        'Noise cancellation ear phone for easier communication.',
        20.40, 17, '1');