class AddSalesUnitToItem < ActiveRecord::Migration[7.0]
  def change
    add_column :items, :sales_unit_id, :integer
    add_index  :items, :sales_unit_id
  end
end
