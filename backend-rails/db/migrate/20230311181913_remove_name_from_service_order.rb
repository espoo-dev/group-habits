class RemoveNameFromServiceOrder < ActiveRecord::Migration[7.0]
  def change
    remove_column :service_orders, :name, :string
  end
end
