class CreateCustomer < ActiveRecord::Migration[7.0]
  def change
    create_table :customers do |t|
      t.string :name, null: false
      t.string :document_number, null: false
      t.string :phone, null: false
      t.string :state_inscription, null: true
      t.integer :customer_type, null: false

      t.string :user_id, null: false, index: true, foreign_key: true

      t.timestamps
    end

    add_index 'customers', %w[user_id name], unique: true
    add_index 'customers', %w[user_id document_number], unique: true
  end
end
