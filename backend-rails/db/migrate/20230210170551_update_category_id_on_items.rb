class UpdateCategoryIdOnItems < ActiveRecord::Migration[7.0]
  def up
    change_column(:items, :category_id, :integer, using: 'category_id::integer')
  end

  def down
    change_column(:items, :category_id, :string, using: 'category_id::string')
  end
end
