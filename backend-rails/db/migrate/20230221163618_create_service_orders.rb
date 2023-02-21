class CreateServiceOrders < ActiveRecord::Migration[7.0]
  def change
    create_table :service_orders do |t|
      t.string :name, null: false
      t.string :extra_info, null: true
      t.string :status, null: false
      t.datetime :creation_date, null: true
      t.datetime :conclusion_date, null: true
      t.decimal :discount, null: true, precision: 8, scale: 2

      t.integer :user_id, null: false, index: true, foreign_key: true
      t.integer :customer_id, null: false, index: true, foreign_key: true

      t.timestamps
    end

    add_index 'service_orders', %w[user_id name], unique: true
  end
end
