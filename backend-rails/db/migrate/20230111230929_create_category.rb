class CreateCategory < ActiveRecord::Migration[7.0]
  def change
    create_table :categories do |t|
      t.string :name, null: false
      t.integer :user_id, null: false, index: true, foreign_key: true

      t.timestamps
    end
    add_index 'categories', ['user_id', 'name'], unique: true
  end
end
