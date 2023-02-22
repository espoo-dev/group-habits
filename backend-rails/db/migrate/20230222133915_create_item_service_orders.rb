class CreateItemServiceOrders < ActiveRecord::Migration[7.0]
  def change
    create_table :item_service_orders do |t|
      t.integer :item_id, null: false, index: true, foreign_key: true
      t.integer :service_order_id, null: false, index: true, foreign_key: true

      t.timestamps
    end
  end
end
