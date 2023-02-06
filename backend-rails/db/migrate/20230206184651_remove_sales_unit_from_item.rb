class RemoveSalesUnitFromItem < ActiveRecord::Migration[7.0]
  def change
    remove_column :items, :sales_unit, :string
  end
end
