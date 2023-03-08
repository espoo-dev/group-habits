class MakeCategoryIdNullable < ActiveRecord::Migration[7.0]
  def up
    change_column :items, :category_id, :integer, null: true
  end

  def down
    change_column :items, :category_id, :integer, null: false
  end
end
