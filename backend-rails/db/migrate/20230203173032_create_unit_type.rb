class CreateUnitType < ActiveRecord::Migration[7.0]
  def change
    create_table :unit_types do |t|
      t.string :name, null: false, uniq: true

      t.timestamps
    end
    add_index :unit_types, [:name], unique: true
  end
end
