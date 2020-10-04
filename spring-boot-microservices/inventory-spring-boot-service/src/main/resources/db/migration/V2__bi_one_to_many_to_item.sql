alter table inventory_item
    add column inventory_id varchar(36);

alter table inventory_item
    add constraint FK_inventory_item_to_inventory_by_inventory_id
        foreign key (inventory_id)
            references inventory (inventory_id);