# This migration comes from active_storage (originally 20191206030411)
class CreateActiveStorageVariantRecords < ActiveRecord::Migration[6.0]
  def change
    # create_table :active_storage_variant_records do |t|
    #   t.belongs_to :blob, null: false, index: false
    #   t.string :variation_digest, null: false

    #   t.index %i[blob_id variation_digest],
    #           name: 'index_active_storage_variant_records_uniqueness',
    #           unique: true
    #   t.foreign_key :active_storage_blobs, column: :blob_id

    #   # To avoid error 'Add timestamps when creating a new table' from Robocop
    #   # From Rubocop docs:
    #   # This cop checks the migration for which timestamps are not included when creating a new
    #   # table. In many cases, timestamps are useful information and should be added.
    #   t.timestamps
    # end
  end
end
