class RenameUnitTypeToSalesUnit < ActiveRecord::Migration[7.0]
  def change
    rename_table :unit_types, :sales_units
  end
end
