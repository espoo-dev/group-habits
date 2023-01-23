class CreateItem < ActiveRecord::Migration[7.0]
  def change
    create_table :items do |t|
      t.string :name, null: false
      t.string :extra_info, null: true
      t.decimal :sale_price, null: false, precision: 8, scale: 2
      t.decimal :purchase_price, null: true, precision: 8, scale: 2
      t.string :sales_unit, null: false
      t.string :item_type, null: false
      t.string :category_id, null: false, index: true, foreign_key: true
      t.string :user_id, null: false, index: true, foreign_key: true

      t.timestamps
    end
    add_index 'items', %w[user_id name], unique: true
  end
end
